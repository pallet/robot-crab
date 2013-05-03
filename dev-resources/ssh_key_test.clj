(ns pallet.crate.ssh-key-test
  (:require
   [clojure.test :refer :all]
   [clojure.tools.logging :as logging]
   [pallet.actions
    :refer
    [directory exec-checked-script file remote-file user]]
   [pallet.algo.fsmop :refer [failed?]]
   [pallet.api :refer [group-spec lift plan-fn]]
   [pallet.build-actions :as build-actions]
   [pallet.common.logging.logutils :refer [logging-threshold-fixture]]
   [pallet.context :as context]
   [pallet.core.user :refer [*admin-user*]]
   [pallet.crate.ssh-key :refer :all]
   [pallet.live-test :as live-test]
   [pallet.script.lib :as lib]
   [pallet.stevedore :as stevedore]
   [pallet.test-utils
    :refer
    [make-localhost-compute
     no-location-info
     test-username
     with-ubuntu-script-template]]
   [pallet.utils :refer [with-temp-file]]))


(ns pallet.crate.ssh-key-test
  (:require
   [clojure.test :refer :all]
   [clojure.tools.logging :as logging]
   [pallet.actions :refer [directory exec-checked-script file remote-file user]]
   [pallet.algo.fsmop :refer [failed?]]
   [pallet.api :refer [group-spec lift plan-fn]]
   [pallet.build-actions :as build-actions]
   [pallet.common.logging.logutils :refer [logging-threshold-fixture]]
   [pallet.context :as context]
   [pallet.core.user :refer [*admin-user*]]
   [pallet.crate.ssh-key :refer :all]
   [pallet.live-test :as live-test]
   [pallet.script.lib :as lib]
   [pallet.stevedore :as stevedore]
   [pallet.test-utils :refer [make-localhost-compute no-location-info
                              test-username with-ubuntu-script-template]]
   [pallet.utils :refer [with-temp-file]]))
