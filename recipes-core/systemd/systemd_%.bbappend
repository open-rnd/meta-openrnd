FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI += " \
        file://watchdog.conf \
        "
CONFFILES_${PN}_append = " ${sysconfdir}/systemd/system.conf.d/10-watchdog.conf"

do_install_append() {

    mkdir -p ${D}${sysconfdir}/systemd/system.conf.d

    # install watchdog configuration
    cp ${WORKDIR}/watchdog.conf ${D}${sysconfdir}/systemd/system.conf.d/10-watchdog.conf
}