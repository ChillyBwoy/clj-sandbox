(ns sandbox.lib.canvas)

(def ^:private app-state (atom {}))

(defn- current-dimensions []
  {:width (.-innerWidth js/window)
   :height (.-innerHeight js/window)})

(defn- update-dimensions [canvas width height]
  (set! (.-width canvas) width)
  (set! (.-height canvas) height)
  (swap! app-state assoc :width width :height height))

(defn- resize-handler [canvas]
  (let [{width :width height :height} (current-dimensions)]
    (update-dimensions canvas width height)))

(defn init [id]
  (let [canvas (.getElementById js/document id)
        context (.getContext canvas "2d")
        {width :width height :height} (current-dimensions)]
    (update-dimensions canvas width height)
    (.addEventListener js/window "resize" #(resize-handler canvas))
    context))
