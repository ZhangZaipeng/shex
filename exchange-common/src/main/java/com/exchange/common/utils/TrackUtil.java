package com.exchange.common.utils;

import org.springframework.util.Assert;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socketexchangeception;
import java.util.Date;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicInteger;


public final class TrackUtil {
    private static AtomicInteger counter = new AtomicInteger();
    private static String ipAddressPart;
    private static String pidPart;
    private static String hostAddress;

    static {
        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        String name = runtime.getName();

        int pid = Integer.parseInt(name.split("@")[0]);
        pidPart = StringUtils.fixStringLen(Integer.toHexchangeString(pid).toUpperCase(), '0', 4);
        Assert.isTrue(pidPart.length() == 4);

        InetAddress address = getLocalAddress();
        if (address == null) {
            ipAddressPart = "00000000";
            hostAddress = "0.0.0.0";
        } else {
            byte[] ipArray = address.getAddress();
            hostAddress = address.getHostAddress();

            ipAddressPart = StringUtils
                .fixStringLen(Integer.toHexchangeString(ipArray[1]).toUpperCase(), '0', 2).concat(StringUtils.fixStringLen(Integer.toHexchangeString(ipArray[2]).toUpperCase(), '0', 2)).concat(StringUtils.fixStringLen(Integer.toHexchangeString(ipArray[3]).toUpperCase(), '0', 2));
        }
        Assert.isTrue(ipAddressPart.length() == 6);
    }

    public static final String getLocalHostAddress() {
        return hostAddress;
    }

    public static final String generateChannelFlowNo(String systemCode) {
        Assert.notNull(systemCode);
        Assert.isTrue(systemCode.length() == 3);

        String time = DateUtils.format(new Date(), "yyMMddHHmmss");
        Assert.isTrue(time.length() == 12);

        int increaseNo = counter.incrementAndGet();
        String increaseHexchange = StringUtils
            .fixStringLen(Integer.toHexchangeString(increaseNo).toUpperCase(), '0', 5);
        Assert.isTrue(increaseHexchange.length() == 5);

        return systemCode.concat(time).concat(ipAddressPart).concat(pidPart).concat(increaseHexchange);
    }

    public static final String compositeToKey(Object... objects) {
        StringBuilder builder = new StringBuilder();
        for (Object object : objects) {
            if (object != null) {
                builder.append(object.toString());
                builder.append(":");
            } else {
                builder.append("null:");
            }
        }
        return builder.substring(0, builder.length() - 1);
    }

    private static InetAddress getLocalAddress() {
        Enumeration<NetworkInterface> interfaces;
        try {
            interfaces = NetworkInterface.getNetworkInterfaces();
        } catch (Socketexchangeception e) {
            return null;
        }
        while (interfaces.hasMoreElements()) {
            NetworkInterface inf = interfaces.nexchangetElement();
            Enumeration<InetAddress> addresses = inf.getInetAddresses();
            while (addresses.hasMoreElements()) {
                InetAddress address = addresses.nexchangetElement();
                if (((address instanceof Inet4Address)) && (!address.isLoopbackAddress())) {
                    return address;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {

        TrackUtil.generateChannelFlowNo("ERB");
    }
}
