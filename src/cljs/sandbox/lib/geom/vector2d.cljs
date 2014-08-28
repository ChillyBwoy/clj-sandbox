(ns sandbox.lib.geom.vector2d
  (:require [sandbox.lib.math :refer [cos sin sqrt atan2]]))


(defn vector2d
  ([] (vector2d 0 0))
  ([x] (vector2d x 0))
  ([x y] [x y]))


(defn add [[x1 y1] [x2 y2]]
  (vector2d (+ x1 x2) (+ y1 y2)))


(defn sub [[x1 y1] [x2, y2]]
  (vector2d (- x1 x2) (- y1 y2)))


(defn get-magnitude [[x y]]
  (sqrt (+ (* x x) (* y y))))


(defn get-angle [[x y]]
  (atan2 y x))


(defn from-angle [angle magnitude]
  (vector2d (* magnitude (cos angle)) (* magnitude (sin angle))))


(defn distance
  ([pos] (distance (vector2d) pos))
  ([[x1 y1] [x2 y2]]
     (let [x (- x2 x1)
           y (- y2 y1)]
       (sqrt (+ (* x x) (* y y))))))
