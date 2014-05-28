(ns sandbox.lib.geom.emitter
  (:require [sandbox.lib.geom.vector2d :refer [vector2d get-magnitude get-angle from-angle]]
            [sandbox.lib.geom.particle :refer [particle]]
            [sandbox.lib.math :refer [PI]]))

(enable-console-print!)

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
  (let [angle (+ (get-angle velocity) (- spread (* (rand) spread 2)))
        magnitude (get-magnitude velocity)]
    (particle pos (from-angle angle magnitude))))

(defn emit-particle-range [em]
  (vec (map #(emit-particle em) (range (:rate em)))))
