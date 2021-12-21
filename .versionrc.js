'use strict'

module.exports = {
  types: [
    { type: 'feat', section: 'Features' },
    { type: 'fix', section: 'Bug Fixes' },
    { type: 'chore', section: 'Chore', hidden: false },
    { type: 'docs', section: 'Docs', hidden: false },
    { type: 'style', section: 'Refactor', hidden: false },
    { type: 'refactor', section: 'Refactor', hidden: false },
    { type: 'perf', section: 'Perf', hidden: false },
    { type: 'test', section: 'Refactor', hidden: false },
    { type: 'build', section: 'Build', hidden: false }
  ],
  bumpFiles: [
    {
      filename: 'VERSION.txt',
      updater: require('./scripts/release/bumpVersion')
    },
    {
      filename: 'README.md',
      updater: require('./scripts/release/changeReadmeVersion')
    },
    {
      filename: 'package.json',
      type: 'json'
    },
    {
      filename: 'package-lock.json',
      type: 'json'
    }
  ]
}
