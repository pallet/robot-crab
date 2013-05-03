(ns robot.crab.format
  (:require
   [clojure.walk :refer [prewalk]]))

(defn read-ns
  [reader]
  (let [ns-form (read reader)]
    (when (= 'ns (first ns-form))
      ns-form)))

(defn sort-if-sequential [form]
  (if (sequential? form)
    (into (empty form) (sort-by str form))
    form))

(defn sort-if-form [form]
  (if (and (sequential? form) (not (instance? clojure.lang.MapEntry form)))
    (cond
     (#{:require :use :import} (first form))
     (concat [(first form)] (sort-by str (rest form)))

     (#{:as :refer :only} (fnext form))
     (vec (list* (first form)
                 (mapcat
                  (fn [[kw v]] [kw (sort-if-sequential v)])
                  (partition 2 (rest form)))))

     :else form)
    form))

(defn sort-form
  [form]
  (concat (take-while (complement sequential?) form)
          (prewalk sort-if-form
                   (drop-while (complement sequential?) form))))

(defn reformat-ns
  [reader]
  (when-let [form (read-ns reader)]
    (sort-form form)))
