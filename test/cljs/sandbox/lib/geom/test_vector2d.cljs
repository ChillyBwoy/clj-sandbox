(ns sandbox.lib.geom.test-vector2d
  (:require-macros [cemerick.cljs.test :refer [are deftest testing]])
  (:require [cemerick.cljs.test :as t]
            [sandbox.lib.geom.vector2d :refer [vector2d add]]))

(deftest test-vector2d
  (testing "test vector2d function"
    (are [expected actual] (= expected actual)
         (vector2d) {:x 0 :y 0}
         (vector2d 10) {:x 10 :y 0}
         (vector2d 10 15) {:x 10 :y 15})))

(deftest test-add
  (testing "test add function"
    (let [v1 (vector2d)
          v2 (vector2d 1 2)
          v3 (vector2d 3 4)
          v4 (vector2d 5 6)]
      (are [expected actual] (= expected actual)
           (vector2d 1 2) (add v1 v2)
           (vector2d 4 6) (add v2 v3)
           (vector2d 8 10) (add v3 v4)))))
