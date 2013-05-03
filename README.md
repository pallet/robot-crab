# robot-crab

A Clojure library to reformat your namespaces.  A more conservative form of
[slamhound](https://github.com/technomancy/slamhound).

    It was Sunday afternoon and he stood with Molly in a sort of
    courtyard. White boulders, a stand of green bamboo, black gravel raked into
    smooth waves. A gardener, a thing like a large metal crab, was tending the
    bamboo...

    The robot crab moved toward them, picking its way over the waves of
    gravel. Its bronze carapace might have been a thousand years old. When it
    was within a meter of her boots, it fired a burst of light, then froze for
    an instant, analyzing data obtained...

    The crab had altered course to avoid her, but she kicked it with a smooth
    precision, the silver boot-tip clanging on the silver carapace. The thing
    fell on its back, but the bronze limbs soon righted it.

    -- Neuromancer, by William Gibson.

Robot-crab reformats your namespace forms.

Add `[robot-crab "0.1.0-SNAPSHOT""]` to the `:dependencies` of your `:user`
profile.

## Leiningen Usage

Make an alias for `run -m robot.crab` in your `:user` profile:

```clj
  :aliases {"robot-crab" ["run" "-m" "robot.crab"]}
```

## License

Copyright © 2013 Hugo Duncan

Distributed under the Eclipse Public License.
