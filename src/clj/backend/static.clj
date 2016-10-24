(ns backend.static
  (:require [common.localization :refer [tr]]
            [clojure.java.io :as io]
            [ring.middleware.content-type :as content-type]
            [hiccup.core :as hiccup]
            [hiccup.page :as page]
            [ring.util.response :as response])
  (:import (org.apache.commons.codec.digest DigestUtils)))

(defn wrap-30d-cache
  [handler]
  (fn [req]
    (let [response (handler req)]
      (if (map? response)
        (assoc-in response
                  [:headers "cache-control"]
                  "public,max-age=2592000,s-maxage=2592000")))))

(defn resource-hash
  [resource-name]
  (if-let [res (io/resource resource-name)]
    (with-open [is (io/input-stream res)]
      (DigestUtils/md5Hex is))))

(def memo-resource-hash (memoize resource-hash))

(defn index
  []
  (hiccup/html
   (page/html5
    [:head
     [:title (tr :page-title)]
     [:meta {:charset "utf-8"}]
     [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge"}]
     [:meta {:name "viewport" :content "width=device-width, initial-scale=1"}]
     [:link {:rel "icon"
             :href (str "/favicon.ico?v="
                        (memo-resource-hash "favicon.ico"))}]
     (page/include-css (str "/css/style.css?v="
                            (memo-resource-hash "css/style.css")))]
    [:body
     [:div#app
      [:div.loading
       [:h1 (tr :loading)]]]
     (page/include-js (str "/js/main.js?v="
                           (memo-resource-hash "js/main.js")))])))

(defn create-handler
  []
  (-> (fn [{:keys [request-method uri] :as req}]
        (if (= request-method :get)
          (if (re-matches #"\/" uri)
             (-> (response/response (index))
                 (response/content-type "text/html"))
             (response/resource-response uri))))
      (content-type/wrap-content-type)
      (wrap-30d-cache)))
