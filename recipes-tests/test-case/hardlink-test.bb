DESCRIPTION = "Hardlink tests, based on simple helloworld application"
SECTION = "examples"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://helloworld.c"

S = "${WORKDIR}/sources"
UNPACKDIR = "${S}"

do_compile() {
	${CC} helloworld.c -c -o helloworld.o
	${AR} rcs libhelloworld.a helloworld.o
}

do_install() {
	install -d ${D}${libdir}
	install -m 0644 libhelloworld.a ${D}${libdir}
	for num in `seq 1 100` ; do
		ln ${D}${libdir}/libhelloworld.a ${D}${libdir}/libhelloworld-${num}.a
	done
}

PACKAGE_STRIP_STATIC = "1"
