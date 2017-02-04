#!/bin/sh

echo '#!/bin/sh\n\nexec java -Xmx512m -jar "$0" "$@"\n\n' | cat - build/libs/kotlin-format.jar > kotlin-format
chmod +x kotlin-format
