(ns robot.crab
  (:require [clojure.java.io :as io]
            [robot.crab.format :refer [reformat-ns]]
            [robot.crab.print :refer [print-ns-form]])
  (:import [java.io File FileReader PushbackReader]))

(defn reformat
  "Reformat the namespace read from `reader`."
  [reader]
  (-> reader
      reformat-ns
      print-ns-form))

(defn write-formatted
  "Write a reformatted version of `file`, returning the new file."
  [file]
  (with-open [rdr (PushbackReader. (io/reader file))]
    (let [new-ns (.trim (reformat rdr))
          tmp-file (doto (File/createTempFile "robotcrab_tmp" ".clj")
                     .deleteOnExit)]
      (io/copy new-ns tmp-file)
      (with-open [writer (io/writer tmp-file :append true)]
        (io/copy rdr writer))
      tmp-file)))

(defn safe-copy-file
  "Copy from-file to to-file.  If any problem occurs, make sure to-file is
   not changed."
  [from-file to-file]
  (let [tmp-file (doto (File/createTempFile "robotcrab_tmp" ".clj")
                   .deleteOnExit)]
    (if (.renameTo to-file tmp-file)
      (try
        (io/copy from-file to-file)
        (catch Exception e
          (.erase to-file)
          (.renameTo tmp-file to-file)
          (throw (ex-info (str "Copy to " to-file " failed") {} e))))
      (throw (ex-info (str "Rename of " to-file " failed") {})))))

(defn -main
  "Takes a file or dir and rewrites the .clj files with formatted ns forms."
  [& file-or-dirs]
  (doseq [file-or-dir file-or-dirs
          file (file-seq (io/file file-or-dir))
          :when (re-find #"/[^\./]+\.clj$" (str file))]
    (try
      (safe-copy-file (write-formatted file) file)
      (catch Exception e
        (println "Failed to reformat:" file)
        (if (System/getenv "DEBUG")
          (.printStackTrace e)
          (println (.getMessage e)))))))
