(ns frontend.subs
  (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
 :hello
 (fn [db _]
   (:hello db)))

(re-frame/reg-sub
 :shout
 (fn [db _]
   (:shout db)))
