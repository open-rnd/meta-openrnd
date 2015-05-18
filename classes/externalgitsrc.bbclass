# openrndexternalsrc.bbclass provides a EXTERNALSRCPV variable to be
# used in SRCREV

EXTERNALSRC_GIT_SRCREV = "${@get_externalsrc_rev(d)}"
EXTERNALSRC_GITPKGV = "${@get_externalsrc_pkgv(d)}"

def __drop_tag_prefix(version):
    if version.startswith('v'):
        return version[1:]
    else:
        return version

def get_externalsrc_rev(d):
    import subprocess
    srcdir = d.expand(d.getVar("EXTERNALSRC"))
    bb.note("external source: %s" % (srcdir))
    rev = subprocess.check_output("cd %s && git rev-list HEAD -1" % (srcdir),
                                  shell=True).strip()
    rev = rev[:7]
    bb.note("got externsrc SRCPV: %s" % (rev))
    return rev

def get_externalsrc_pkgv(d):
    import subprocess
    srcdir = d.expand(d.getVar("EXTERNALSRC"))
    bb.note("external source: %s" % (srcdir))
    desc = subprocess.check_output("cd %s && git describe HEAD --always" % (srcdir),
                                  shell=True).strip()
    out = __drop_tag_prefix(desc)
    bb.note("got externalsrc PKGV: %s" % (out))
    return out

