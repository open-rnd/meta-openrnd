SUMMARY = "Configuration for mobile broadband services"
SECTION = "base"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
SRC_URI = " \
        file://apn-generic \
        file://mobile-modem.chat \
        file://mobile-auth \
        file://mobile-noauth \
        file://options-mobile \
        file://10-ppp.network \
        file://resolved-ip-up \
        file://resolved-ip-down \
"

PV = "0.1"
PR = "r4"

RDEPENDS_${PN} = "\
               ppp \
               systemd (>= 211) \
               "

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
    ln -s apn-generic ${chatscripts}/apn

    # install systemd file for ppp so that networkd knows about these
    # interfaces
    install -d ${D}${sysconfdir}/systemd/network
    install -m 0644 -t ${D}${sysconfdir}/systemd/network ${S}/10-ppp.network

    # install systemd ppp-ifup/ifdown hook scripts
    install -d ${D}${sysconfdir}/ppp/ip-down.d
    install -d ${D}${sysconfdir}/ppp/ip-up.d
    install -m 0755 -t ${D}${sysconfdir}/ppp/ip-up.d ${S}/resolved-ip-up
    install -m 0755 -t ${D}${sysconfdir}/ppp/ip-down.d ${S}/resolved-ip-down
}

do_configure[noexec] = "1"
do_compile[noexec] = "1"
