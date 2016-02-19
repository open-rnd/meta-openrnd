DESCRIPTION = "Local package feeds for opkg "
LICENSE = "CLOSED"

PACKAGE_ARCH = "${MACHINE_ARCH}"
INHIBIT_DEFAULT_DEPS = "1"

FEEDADDR ?= ""

FEEDFILE = "${S}/local-feeds.conf"

do_compile() {

    if [ -z "${FEEDADDR}" ]; then
        bberror "FEEDADDR not configure, please add FEEADDR_pn-${PN} = \"some-address\" to local.conf"
    fi
    for arch in ${PACKAGE_ARCHS}; do
        echo "src/gz $arch ${FEEDADDR}$arch" >> ${FEEDFILE}
    done
}

do_install() {
    install -d ${D}${sysconfdir}/opkg
    install -m 0644 -t ${D}${sysconfdir}/opkg ${FEEDFILE}
}

FILES_${PN} = " \
    ${sysconfdir}/opkg/local-feeds.conf \
"
