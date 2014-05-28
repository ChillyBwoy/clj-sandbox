(ns sandbox.lib.geom.test-particle
  (:require-macros [cemerick.cljs.test :refer [are deftest testing]])
  (:require [cemerick.cljs.test :as t]
            [sandbox.lib.geom.vector2d :refer [vector2d]]
            [sandbox.lib.geom.particle :refer [particle]]))

(deftest test-particle
  (testing "test particle function"
    (let [p1 (particle)
          p2 (particle (vector2d 1 2))
          p3 (particle (vector2d 1 2) (vector2d 3 4))
          p4 (particle (vector2d 1 2) (vector2d 3 4) (vector2d 5 6))]
      (are [expected actual] (= expected actual)
           p1 {:pos {:x 0 :y 0} :velocity {:x 0 :y 0} :acceleration {:x 0 :y 0} :color "#FFF"}
           p2 {:pos {:x 1 :y 2} :velocity {:x 0 :y 0} :acceleration {:x 0 :y 0} :color "#FFF"}
           p3 {:pos {:x 1 :y 2} :velocity {:x 3 :y 4} :acceleration {:x 0 :y 0} :color "#FFF"}
           p4 {:pos {:x 1 :y 2} :velocity {:x 3 :y 4} :acceleration {:x 5 :y 6} :color "#FFF"}))))
