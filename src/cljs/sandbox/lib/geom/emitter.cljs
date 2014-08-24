(ns sandbox.lib.geom.emitter
  (:require [sandbox.lib.geom.vector2d :as v2d]
            [sandbox.lib.geom.point :as point]
            [sandbox.lib.math :refer [PI]]))

(defn emitter
  ([pos velocity] (emitter pos velocity (/ PI 32) 4))
  ([pos velocity spread] (emitter pos velocity spread 4))
  ([pos velocity spread rate]
   {:pos pos
    :velocity velocity
    :spread spread
    :rate rate
    :color "#999"}))


(defn emit-particle [{:keys [pos velocity spread]}]
  (let [angle (+ (v2d/get-angle velocity) (- spread (* (rand) spread 2)))
        magnitude (v2d/get-magnitude velocity)]
    (point/create pos pos (v2d/from-angle angle magnitude))))


(defn emit-particle-range [em]
  (vec (map #(emit-particle em) (range (:rate em)))))
