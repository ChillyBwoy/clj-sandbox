(ns sandbox.lib.canvas)

(declare width)
(declare height)
(declare current-dimensions)

(def ^:private app-state (atom {:mouse [(* (.-innerWidth js/window) 0.5)
                                        (* (.-innerHeight js/window) 0.5)]}))


(defn- update-dimensions [canvas width height]
  (set! (.-width canvas) width)
  (set! (.-height canvas) height)
  (swap! app-state assoc :width width :height height))


(defn- resize-handler [canvas]
  (let [[width height] (current-dimensions)]
    (update-dimensions canvas width height)))


(defn- mousemove-handler [event]
  (swap! app-state assoc :mouse [(.-clientX event) (.-clientY event)]))


(defn mouse-position [] (:mouse @app-state))


(defn width []
  (.-innerWidth js/window))


(defn height []
  (.-innerHeight js/window))


(defn current-dimensions []
  [(width) (height)])


(defn init [id]
  (let [canvas (.getElementById js/document id)
        context (.getContext canvas "2d")
        [width height] (current-dimensions)]
    (update-dimensions canvas width height)
    (.addEventListener js/window "resize" #(resize-handler canvas))
    (.addEventListener js/window "mousemove" mousemove-handler)
    context))
