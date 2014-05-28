(ns sandbox.lib.geom.vector2d
  (:require [sandbox.lib.math :refer [cos sin sqrt atan2]]))

(defn vector2d
  ([] (vector2d 0 0))
  ([x] (vector2d x 0))
  ([x y] {:x x :y y}))

(defn add [{x1 :x y1 :y} {x2 :x y2 :y}]
  (vector2d (+ x1 x2) (+ y1 y2)))

(defn get-magnitude [{x :x y :y}]
  (sqrt (+ (* x x) (* y y))))

(defn get-angle [{x :x y :y}]
  (atan2 y x))

(defn from-angle [angle magnitude]
  (vector2d (* magnitude (cos angle)) (* magnitude (sin angle))))


