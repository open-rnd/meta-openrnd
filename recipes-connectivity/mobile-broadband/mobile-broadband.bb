SUMMARY = "Configuration for mobile broadband services"
SECTION = "base"
LICENSE = "GPLv2+"
SRC_URI = " \
        file://apn-generic \
        file://mobile-modem.chat \
        file://mobile-auth \
        file://mobile-noauth \
        file://options-mobile \
"

PV = "0.1"
PR = "r1"

RDEPENDS_${PN} = "ppp"

S = "${WORKDIR}/"

do_install () {
    pppdir=${D}${sysconfdir}/ppp
    chatscripts=${pppdir}/chatscripts
    peers=${pppdir}/peers
    mkdir -p ${chatscripts} ${peers}

    for f in apn-generic mobile-modem.chat; do
        cp -a ${S}/${f} ${chatscripts}/
    done

    for f in mobile-auth mobile-noauth; do
        cp -a ${S}/${f} ${peers}/
    done

    cp -a ${S}/options-mobile ${pppdir}/

    # setup default links
    ln -s mobile-noauth ${peers}/mobile
    ln -s mobile ${peers}/provider
    ln -s apn-generic ${chatscripts}/apn
}

do_configure[noexec] = "1"
do_compile[noexec] = "1"
