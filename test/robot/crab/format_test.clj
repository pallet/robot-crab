(ns robot.crab.format-test
  (:require
   [clojure.test :refer :all]
   [robot.crab.format :refer :all]))

(deftest sort-form-test
  (is (= '(ns fred
             "abc"
             {:author "X"}
             (:require
              [clojure.test :as test]
              [robot.crab :as crab :refer [reformat rewrite]])
             (:import [c b a]))
         (sort-form
          '(ns fred
             "abc"
             {:author "X"}
             (:require
              [robot.crab :as crab :refer [rewrite reformat]]
              [clojure.test :as test])
             (:import [c b a]))))))
