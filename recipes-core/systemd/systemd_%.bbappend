FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI += " \
        file://watchdog.conf \
        file://sysctl-panic-on-oops.conf \
        "
CONFFILES_${PN}_append = " ${sysconfdir}/systemd/system.conf.d/10-watchdog.conf"

do_install_append() {

    install -d ${D}${sysconfdir}/systemd/system.conf.d

    # install watchdog configuration
    install -m 644 ${WORKDIR}/watchdog.conf \
                   ${D}${sysconfdir}/systemd/system.conf.d/10-watchdog.conf

    install -d ${D}${sysconfdir}/sysctl.d
    install -m 644 ${WORKDIR}/sysctl-panic-on-oops.conf \
                   ${D}${sysconfdir}/sysctl.d/10-panic-on-oops.conf
}