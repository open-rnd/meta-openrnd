SUMMARY = "Support files for using USB dongle modems with mobile-broadband"
SECTION = "base"
LICENSE = "GPLv2+"
SRC_URI = " \
        file://99-huawei-modem.rules \
"

PV = "0.1"
PR = "r1"

RDEPENDS_${PN} = "systemd udev mobile-broadband"
DEPENDS = "udev"

S = "${WORKDIR}/"

do_install () {
    udevdir=$(pkg-config --variable=udevdir udev)
    rulesdir=${D}${udevdir}/rules.d

    mkdir -p ${rulesdir}

    cp -a 99-huawei-modem.rules ${rulesdir}/
}

do_configure[noexec] = "1"
do_compile[noexec] = "1"
