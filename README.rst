=====================
 OpenRnD Yocto Layer
=====================

This is a Yocto layer definition of pacakges by Open-RnD and their
deps.

Dependencies
============

The layer may possible depend on:
- meta-openembedded
- meta-fsl-arm, meta-fsl-arm-extra (iMX6 support)
- meta-ti (OMAP 35xx - BeagleBone, BeagleBoard)
- meta-raspberrypi (Raspberry Pi support)

Extras
==========

The layer defines a base distribution ``openrnd-poky-systemd`` that
should be used as a base for any builds. The distribution uses
``systemd`` by default.
