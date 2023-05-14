(ns roller-ball.middleware.wrap-disable-cors)

(defn wrap-disable-cors [handler]
  (fn [req]
    (let [resp (handler req)]
      (assoc resp
        :headers (assoc (:headers resp)
                   "Access-Control-Allow-Origin" "*"
                   "Access-Control-Allow-Headers" "Origin, X-Requested-With, Content-Type, Accept"
                   "Access-Control-Allow-Methods" "GET, POST, PUT, DELETE, OPTIONS")))))

