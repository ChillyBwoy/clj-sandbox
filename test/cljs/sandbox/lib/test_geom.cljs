(ns sandbox.lib.test-geom
  (:require-macros [cemerick.cljs.test :refer [are deftest testing]])
  (:require [cemerick.cljs.test :as t]
            [sandbox.lib.geom.vector2d :as v2d]
            [sandbox.lib.geom.point :as point]))


(deftest test-vector2d

  (testing "test vector2d function"
    (are [expected actual] (= expected actual)
         (v2d/create) {:x 0 :y 0}
         (v2d/create 10) {:x 10 :y 0}
         (v2d/create 10 15) {:x 10 :y 15}))

  (testing "test add function"
    (let [v1 (v2d/create)
          v2 (v2d/create 1 2)
          v3 (v2d/create 3 4)
          v4 (v2d/create 5 6)]
      (are [expected actual] (= expected actual)
           (v2d/create 1 2) (v2d/add v1 v2)
           (v2d/create 4 6) (v2d/add v2 v3)
           (v2d/create 8 10) (v2d/add v3 v4)))))


(deftest test-point

  (testing "testing point/create function"
    (let [p1 (point/create)
          p2 (point/create (v2d/create 1 2))
          p3 (point/create (v2d/create 1 2) (v2d/create 3 4))
          p4 (point/create (v2d/create 1 2) (v2d/create 3 4) (v2d/create 5 6))
          p5 (point/create (v2d/create 1 2) (v2d/create 3 4) (v2d/create 5 6) (v2d/create 7 8))]
      (are [expected actual] (= expected actual)
           p1 {:pos {:x 0 :y 0} :old-pos {:x 0 :y 0} :velocity {:x 0 :y 0} :acceleration {:x 0 :y 0} :size 2 :color "#880088"}
           p2 {:pos {:x 1 :y 2} :old-pos {:x 0 :y 0} :velocity {:x 0 :y 0} :acceleration {:x 0 :y 0} :size 2 :color "#880088"}
           p3 {:pos {:x 1 :y 2} :old-pos {:x 3 :y 4} :velocity {:x 0 :y 0} :acceleration {:x 0 :y 0} :size 2 :color "#880088"}
           p4 {:pos {:x 1 :y 2} :old-pos {:x 3 :y 4} :velocity {:x 5 :y 6} :acceleration {:x 0 :y 0} :size 2 :color "#880088"}
           p5 {:pos {:x 1 :y 2} :old-pos {:x 3 :y 4} :velocity {:x 5 :y 6} :acceleration {:x 7 :y 8} :size 2 :color "#880088"}))))
