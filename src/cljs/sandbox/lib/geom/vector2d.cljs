(ns sandbox.lib.geom.vector2d
  (:require [sandbox.lib.math :as math]))

(defn vector2d
  ([] {:x 0 :y 0})
  ([x y] {:x x :y y}))

(defn add [{x1 :x y1 :y :as v1} {x2 :x y2 :y :as v2}]
  (vector2d (+ x1 x2) (+ x2 y2)))

(defn magnitude [{:keys [x y]}]
  (math/sqrt (+ (* x x) (* y y))))

(defn angle [{:keys [x y]}]
  (math/atan2 y x))

(defn from-angle [angle magnitude]
  (vector2d (* magnitude (math/cos angle)) (* magnitude (math/sin angle))))


