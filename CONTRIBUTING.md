# Contributing to our 2024 Codebase.
This guide assumes you have already read the README.

## Git
Whenever you start on something new create a new branch from your master branch. The branch name should describe what it aims to achieve. When you are done you should open a pull request to WARP2024 master.

## Git History
Try and keep git history clean. Commit messages should explain in a moderate amount of detail what has changed.
An example of good Git usage would be as follows :
```bash
git add .
git commit -m '_commitmessage_' eg 'Fixed intake not holding in idle mode'
git push _remotename_ _branchname_ eg origin intake-fix
```

## Creating Issues
Issues should be created to report bugs or request features. Use the appropriate template for your issue and make sure to apply the appropriate labels. If you are working on an issue assign yourself to the issue.

## Creating Pull Requests
Pull requests should be created to resolve issues. Reference the issue you are resolving in your pull request. Pull requests are required to pass continuous integration checks that run automatically. This will include formatting, tests and build checks.

## End
Good luck on your contribution trip, this may be used as a template for the 2025 CONTRIBUTING.md .
