(ns roller-ball.api.POST-upload-object
  (:require [ring.util.response :refer [response]])
  (:require [roller-ball.storage.s3-ops :as s3RB]))

(defn get-params [req]
  (get-in req [:params "obj"]))

(defn POST-upload-object-handler [request]
  ((try
     (let [file-data
           (-> request
               (get-params))]
       (s3RB/upload-new-object "test-4" file-data)
       (response {:ok "ok"})
       )
     (catch Exception e
       (println (.getMessage e))
       ))))
