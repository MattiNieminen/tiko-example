(ns frontend.events
  (:require-macros [cljs.core.async.macros :as a])
  (:require [cljs.core.async :as a]
            [frontend.sente :as sente]
            [frontend.db :as db]
            [re-frame.core :as re-frame]))

;;
;; Sente-related
;;

(defn dispatch-sente-event-msg
  [{:keys [id] :as ev-msg}]
  (re-frame/dispatch [id ev-msg]))

(re-frame/reg-fx
 :sente-connection
 (fn [_]
   (sente/connect! dispatch-sente-event-msg)))

(re-frame/reg-fx
 :sente-event
 (fn [{:keys [event dispatch-to]}]
   (if dispatch-to
     (sente/send! event #(re-frame/dispatch (conj dispatch-to %1)))
     (sente/send! event))))

(re-frame/reg-event-fx
 :connect-sente
 (fn [_ _]
   {:sente-connection true}))

(re-frame/reg-event-db
 :chsk/state
 (fn [db [_ ev-msg]]
   (let [[id [_ new-state]] (:event ev-msg)]
     (assoc db :sente new-state))))

(re-frame/reg-event-fx
 :chsk/handshake
 (fn [_ _]
   ;; Do nothing on handshake. Could fetch initial data etc...
   ))

(re-frame/reg-event-fx
 :chsk/recv
 (fn [_ [_ ev-msg]]
   {:dispatch (:?data ev-msg)}))

;;
;; App-specific
;;

(re-frame/reg-event-db
 :initialise-db
 (fn [_ _]
   db/default-value))

(re-frame/reg-event-fx
 :say-hello
 (fn [_ [_ message]]
   {:sente-event {:event [:friends/hello {:message message}]
                  :dispatch-to [:handle-hello-response]}}))

(re-frame/reg-event-db
 :handle-hello-response
 (fn [db [_ {:keys [message]}]]
   (assoc db :hello message)))

(re-frame/reg-event-db
 :friends/shout
 (fn [db [_ {:keys [message]}]]
   (assoc db :shout message)))
