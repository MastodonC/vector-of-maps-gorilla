# vector-of-maps gorilla
just want to be able to display my vector of maps in a nice tabular format.
Based on core-matrix-gorilla (developed by Kiran Karkera)

## Usage

Add
[![Clojars Project](https://img.shields.io/clojars/v/vector-of-maps-gorilla.svg)](https://clojars.org/vector-of-maps-gorilla)
 to your project's dependencies. You will also need to have the lein-gorilla plugin in your project's plugin vector (see
Gorilla REPL's [getting started](http://gorilla-repl.org/start.html) page for help with installing Gorilla).
You can then `(use 'vector-of-maps-gorilla.render)` in your Gorilla worksheets.

In your file: if you have a vector of maps:

```
(table-view [{:a 1 :b 2}{:a 3 :b 4}])
```

There is an optional last parameter for options.  Current options: `cols`, to explicitly name (and order) the columns we want to use, and `nrows`, to have a different number than the 10 default rows.

```
(table-view [{:a 1 :b 2}{:a 3 :b 4}] {:cols [:a] :nrows 15)
```

Caveats: if you have unusual types in your map (as in not primitives), you might have to define the rendering for them your code - they need to implement the Renderable interface from [gorilla-renderable](https://github.com/JonyEpsilon/gorilla-renderable).

Copyright © 2017- Elise Huard
