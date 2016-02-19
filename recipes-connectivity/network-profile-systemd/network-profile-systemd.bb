DESCRIPTION = "Default systemd-networkd profiles that enable DHCP on interfaces"

inherit allarch

PR = "r3"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI = "\
    file://10-default-eth.network \
    file://10-default-wlan.network \
"

S = "${WORKDIR}/"

do_install() {
    install -d ${D}${sysconfdir}/systemd/network
    install -m 0644 -t ${D}${sysconfdir}/systemd/network ${WORKDIR}/10-default-eth.network

    # install networkd for wlan only if wifi support is enabled
    if ${@bb.utils.contains('DISTRO_FEATURES', 'wifi', 'true', 'false', d)}; then
        install -m 0644 -t ${D}${sysconfdir}/systemd/network ${WORKDIR}/10-default-wlan.network
    fi
}

PACKAGES = "${PN}"

RDEPENDS_${PN} = "systemd (>= 211)"