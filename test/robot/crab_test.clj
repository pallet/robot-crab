(ns robot.crab-test
  "Test the crab
abc"
  {:author "Hugo Duncan"}
  (:require
   [clojure.test :refer :all]
   [clojure.java.io :refer [reader resource file] :as io]
   [robot.crab :refer :all])
  (:import [java.io PushbackReader]))

(deftest reformat-test
  (is (=
       "(ns robot.crab-test
  \"Test the crab
abc\"
  {:author \"Hugo Duncan\"}
  (:require
   [clojure.java.io :refer [file reader resource] :as io]
   [clojure.test :refer :all]
   [robot.crab :refer :all])
  (:import
   [java.io PushbackReader]))
"
       (reformat
        (PushbackReader.
         (reader (.getPath (file (resource "robot/crab_test.clj")))))))))
