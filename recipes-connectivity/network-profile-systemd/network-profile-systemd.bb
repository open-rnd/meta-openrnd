DESCRIPTION = "Default network profile that enables DHCP for all eth interfaces, to be used with  systemd-networkd"

PR = "r2"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI = "file://10-default.network"

S = "${WORKDIR}/"

do_install() {
             install -d ${D}${sysconfdir}/systemd/network
             install -m 0644 -t ${D}${sysconfdir}/systemd/network ${WORKDIR}/10-default.network
}

PACKAGES = "${PN}"

RDEPENDS_${PN} = "systemd (>= 211)"