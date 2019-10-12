const fs = require('fs');
const path = require('path');

const walkSync = (d) => fs.statSync(d).isDirectory() ? fs.readdirSync(d).map(f => walkSync(path.join(d, f))) : d;
let path1 = process.argv[2];

console.log(walkSync(path1))

