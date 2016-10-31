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
   {:dispatch [:fetch-todos]}))

(re-frame/reg-event-fx
 :chsk/recv
 (fn [_ [_ ev-msg]]
   {:dispatch (:?data ev-msg)}))

(re-frame/reg-event-fx
 :chsk/ws-ping
 (fn [_ _]
   ;; Do nothing on ping.
   ))

;;
;; App-specific
;;

(re-frame/reg-event-db
 :initialise-db
 (fn [db _]
   (merge db/default-value db)))

(re-frame/reg-event-fx
 :fetch-todos
 (fn [_ [_ message]]
   {:sente-event {:event [:todo/fetch-all]
                  :dispatch-to [:handle-todos-response]}}))

(re-frame/reg-event-db
 :handle-todos-response
 (fn [db [_ {:keys [todos]}]]
   (assoc db :todos todos)))

(re-frame/reg-event-db
 :update-new-todo
 (fn [db [_ {:keys [new-todo]}]]
   (assoc db :new-todo new-todo)))

(re-frame/reg-event-fx
 :create-todo
 (fn [{:keys [db]} _]
   {:sente-event {:event [:todo/create (select-keys db [:new-todo])]}
    :db (dissoc db :new-todo)}))

(re-frame/reg-event-db
 :todo/push-all
 (fn [db [_ {:keys [todos]}]]
   (assoc db :todos todos)))
