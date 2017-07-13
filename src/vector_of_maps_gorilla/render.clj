(ns vector-of-maps-gorilla.render
  (:require [gorilla-renderable.core :as render]))

(defn list-like
  "util function used in render"
  [data value open close separator]
  {:type :list-like
   :open open
   :close close
   :separator separator
   :items data
   :value value})

(defn renderfn
  "Assumption: all keys of interest are represented in the first 10 rows"
  ([self] (renderfn self {}))
  ([self {:keys [nrows cols] :as opts}]
   (let [nrows (or nrows 10)
         column-names (->> self
                           (take 10)
                           (map keys)
                           flatten
                           set)
         columns (if cols
                   (clojure.set/intersection (set keys) column-names)
                   column-names)
         rendfn (fn [open close sep r] (list-like (map render/render r) (pr-str r) open close sep))
         rows (map (partial rendfn "<tr><td>" "</td></tr>" "</td><td>")
                   (map #(vals (select-keys % columns)) (take nrows self)))
         heading [(rendfn "<tr><th>" "</th></tr>" "</th><th>" columns)]
         body (list-like (concat heading rows) (pr-str self) "<center><table>" "</table></center>" "\n")]
     body)))

;;Created another type just to pass parameters such as num rows and num columns to mview function
(defrecord VoMView  [contents opts])

(extend-type VoMView
  render/Renderable
  (render [self & opts] (renderfn (:contents self) (:opts self))))

(defn table-view
  "view the dataset. Accepts an optional map with :nrows and :ncols keys for
  number of rols and/or columns to display"
  ([dataset] (table-view dataset {}))
  ([dataset {:keys [nrows cols] :as opts}]
   (VoMView. dataset opts)))
