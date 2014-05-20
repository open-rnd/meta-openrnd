# base class for Open-RnD packages, defines:
# - OPENRND_GIT - URI to Open-RnD git repo
# - S - default source directory pointing to git clone dir
# - SRC_URI - default git repository location (composed of OPENRND_GIT
#   and OPENRND_REPO_NAME)
#
# inheriting package should define:
# - OPENRND_REPO_NAME - repository name in Open-RnD's gerrit


OPENRND_GIT = "git://open-rnd.net:29418"
SRC_URI = "${OPENRND_GIT}/${OPENRND_REPO_NAME};protocol=ssh"

# we're fetching all packages from git, set default source dir

S = "${WORKDIR}/git"

