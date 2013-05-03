(ns robot.crab.print
  "Format a namespace declaration using pretty print with custom dispatch."
  (:require
   [fipp.printer :refer [pprint-document]]
   [fipp.edn :refer [-pretty]]
   [fipp.clojure :refer [pprint pretty]]))

(extend-protocol fipp.edn/IPretty
  clojure.lang.ISeq
  (-pretty [s]
    (cond
     (#{'ns} (first s))
     [:group "(" [:align 1 (concat
                            [(-pretty (first s)) :text " "]
                            (interpose :line (map -pretty (rest s))))] ")"]

     (some #{:as :refer} s)
     [:group "(" [:align
                  (concat
                   [(-pretty (first s)) :line]
                   (interpose :line
                              (mapcat
                               (fn [[kw v]]
                                 [:nest 2 (-pretty kw) (-pretty v)])
                               (partition 2 (rest s)))))] ")"]

     (#{:require :use :import} (first s))
     [:group "(" [:align (-pretty (first s))
                  :break (interpose :break (map -pretty (rest s)))] ")"]

     :else [:group "(" [:align (interpose :line (map -pretty s))] ")"]))

  clojure.lang.IPersistentVector
  (-pretty [v]
    (cond
     (some #{:as :refer} v)
     [:group "[" [:align
                  (concat
                   [(-pretty (first v))]
                   (map
                    (fn [[kw v]]
                      [:group :line (-pretty kw) :text " " (-pretty v)])
                    (partition 2 (rest v))))] "]"]

     :else [:group "[" [:align (interpose :line (map -pretty v))] "]"]))

  java.lang.String
  (-pretty [s]
    [:text (str "\"" s "\"")]))

(defn my-pretty [x]
  (fipp.edn/-pretty x))

(defn print-ns-form [form]
  (let [p (my-pretty form)]
    (with-out-str (pprint-document p {:width 80}))))
