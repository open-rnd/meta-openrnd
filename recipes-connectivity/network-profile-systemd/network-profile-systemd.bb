DESCRIPTION = "Default network profile that enables DHCP for all eth interfaces, to be used with  systemd-networkd"

PR = "r0"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690"

SRC_URI = "file://default.network"

do_install() {
             install -d ${D}${sysconfdir}/systemd/network
             install -m 0644 -t ${D}${sysconfdir}/systemd/network ${WORKDIR}/default.network
}

PACKAGES = "${PN}"

RDEPENDS_${PN} = "systemd (>= 211)"