(ns roller-ball.api.GET-health-check
  (:require [clj-time.format :as time-format]
            [clj-time.core :as time])
  (:require [ring.util.response :refer [response]]))

(def ok "ok")

(defn current-time []
  (let [fmt (time-format/formatter "MM/dd/yyyy - HH:mm:ss")]
    (time-format/unparse fmt (time/now))))

(defn GET-health-check-handler [req]
  (response {:status ok :time (str (current-time))}))

