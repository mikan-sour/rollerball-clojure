(ns roller-ball.middleware.wrap-favicon-not-found)

(defn wrap-favicon-not-found [handler]
  (fn [request]
    (if (= "/favicon.ico" (:uri request))
      {:status 404}
      (handler request))))