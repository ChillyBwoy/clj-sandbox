(ns sandbox.apps.fps)

(defn- render [ctx])

(defn ^:export run []
  (print "start")
  (let [context (canvas/init "display")]
    (render context)))
