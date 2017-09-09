import com.kokmobi.server.service.*;
import com.kokmobi.server.service.RedisTool;
import com.kokmobi.server.util.DesUtils;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/3/18
 *          Time: 10:25
 * @author: Administrator
 * @since 3.0
 */
public class TestMain {
    public static void main(String[] args) {
        com.kokmobi.server.service.RedisTool.set("aa","a");
        System.out.println(RedisTool.get("aa") );
       /* String str = "jyY59buuO*twcN3myEKPjwuolgYYB5Pmv4OXrowW2HF*OUgPAzCka9JHYrQ3 VRiQ7RDsd/tr0ZgUQ7YO7QZc*Zk6Vz467V9nLWwK71f06z2oBjuQEO8jRuNX ArYPjSBFbG14oP*//*VkN8*bXwXhHd5rL6Svum6Yg30R4XMp*o*s*s9cMA*hs0 SSmTphnn0QB2ZxX2BwOTzPEcw3/9HjahaVguDjCkmMHgujavMMze*Rv7qB39 zPj9Q1uU9/7huhWM2CUlGr2itniG5C6NbhsI1Zv0n5dXCNNk7EKKOJU*vEmO w/zHHCgyN3IkwgjJhr19wRmWOU9h8PLVUbYSBeShAnI9hcBZtC/aU3aGoG40 ZIhSRtlNjsEN/RduKUt0w/ZkKjuBa6enfgSWSkO6pz*JCoM8vfvLz*fx827b FrbI89VVHuDGgGou4J9rQ*iP1LFenHv6Sriutft3nm*w6Gc8nDop9jMCo4f0 /sxvcCStgbXYIzEhNdOMx1GkBCkAjI0amREkxXg*HzoAj/SBv4P4Ihm025qh 0tm92XV0dW9bELErvYfid46SXyuQZGQzkCmKvYpl9L*IGXhJRjZc6n41yd6g *FX1XQzQquO*K2NNlxB*iGSZvu/U*JQg78TYabVcENLS64YpLuuebsoad4Ek /DjuqHznh*s46ASPdkg/1F7eJzixEmDuZC5RYVxa24gxrgbRcTe18iv9O3Hm RGqmIwGo4aQQIYan84nqoYJ0UyzWXB3NFI4RRhpQGj9faG1HXuziHF4N0hts FZaSQNFcgGLuz95mPXwTvYs5WqUiWqbpBDn1pXz4BvNH3HxeiokKcvdwSQpg 0l1UCtoVt1jvZnkzwfOk4p28NJYuI3oKnw4W*651udpLdRxXE220vjjlnDEU BmLnvKw0078wE66dUPDs*iTN9mO1G6iArTJ5Wwa4y1fxc49klhhDrlJ5cf1p 14kzyMiFgHWXxnuz5Gy*5Rg7RT/wE/JXfsfqvZ7G/tiEE9jDOAsU3QNUX0ge Ih7LkM69xEyFnmrcHsbtiHVfkmBAbUXoUdOSHtdk5fPzfXLbbBcXc4CtTB6j tVajaXMefhjsBOiJ74669lbYU/rsr2XOR07heQSxb0CzKcXsI9nJKrOSKUWR EVwKPRqinTz*KQCU0cymlgvnieDhL**eIwYpH3jvaex2mq0wi0306KyHYPWp acQD7QITjfHFEHWUTMUcz51H9BLZCkai5d/JMlyy/nxv7pvXxlecooCQ7HZz *YpODPha21C58YwHYudHG0gfEl8zjQvqddts/Kdq*kY67pAIzisHHvIHTzsC nyf7eHDI3K8AOti/5O2bO4jT6SEhRn6QO8U3JNvgOZUpeZvyU0D/Rl/ItTPP UHW1j/sxvgmAjpiiAoWb9u53GlzEt5X0ofC58kxkTpHv*PR3V0V39RIpw0Vi y4wBDeh/AZGoPrv6jvihmmYxqGT77RKwqe259xgd3BP6hY4qKrVSgbGVDOR1 cVQXjVGjCXP4dCV9jm6xaPYX5fnUUsPmkeDlg5I8IxrCoH0*6IRiw0FTP3DR l4ZgHQJb49*2lRDosl0fXNRCcNxLqQyzRCv/13NDg0Cmv93ohc2uDqXwCRwN 6fcMyqsmiYnZoOS*24ffLfveNBLd9d7zeYQEy7MOH0uEhjRylENQrXj9rNY2 0WJvTtswW7Rt6NucxXABgxq66UsqhSVmJzBCXfNXphBYSBr0r/Vt29dwAL9K t9OQlv3q/HlwLz/UuAOVDuQqWJ1e6/ILmx86Mukovxyjsbd0giwUuL4r7pcI UKZvCrFLDIQyU1nCnOY3I7kteyIfdYv6aC23PyfSPVOiB8UOw68/SDYsn*4u juYlDUx73SysQ0taSbmjzNSCrCqc3OPKqwe7GGC36uB3bfjhcS1dwSZu686n DMHRhG7HZwn9ScZ03SLIe/qmRTIp6oiDmEGWyoIZGO36Su10JqRE2AgZ5ZmN YMru77MtcnpmQMnuMi21YAfGN0sJjSIFf5CWqRvZlH9klu0SxU/Auyc/C2pB s3HpoZUTTV14/tV1v/ZQftvgeYIa39uxsA/9VfEyxj2IsVjelDbL1TB8UxZA jGyTRYZcQsfdWB82zdeU8B/jlQibw9c1HGEMvD2uKyH*yohb5v8MnCNbe42Q Z1MwL/ZxS*0BuGR4XHM55T/vAEGTbOCbIy3H4UMepiYYLKRyMP4Fp/cQTaX2 pmqE9nf4DIB/8PXGxjipwJdpNcWe4mMDSfJCNhmEfjTr/6X3Hv0txMNrVLmC SfLoVMW5EZmbzq6QnyzTzL0pQYqoWBJyHSltUF2C4jz1PbYpQ1ZLyGJUmqC0 j3mt84N18UYi25VCcBVjiUlIaMigYTeikyw1BzG3pOMACnxUoM0tzdnKINIm R9rT4bZ/LVGYOgxM6cJjiNX94MJYt25AFjeY8JKiG5rO6a1T9IIhmeCknaPY wO5m1MymqathaJXNMcQg2X92F4k3dAEvbtmTVo5I0X8cVKIFJuZ4wD1R75Qu OeTUeM*qg1b5NJ1kHkaiMGEXvokRuwEh9sRQNxggbzCBMUAaNmHfnBHhXEbR 94*CScfdvVeMaxntPmjnFiQdYJXQniDoWNgY3hMbWLz69THErfH5UAjD1E9x LHK*SPyDCie7PgFsr/gJZprOp2FMdEFwe0qh*TRVE976216MYgxv1UFwUFKe CE7qsbgXSjnRv3PxZYeknYDYuVwaYEe/GXWSeX3bG0cWjtoBYJw6Chh7oy/l b*6W3kBLxviysEvDICsR5sYMtOswZ3zap9TmZdoZh4QW8ZE4pH8NAILsE9jP Ccvb13hOI0BSooQbrBG7j7HuICq//BugLZwMUrr6ZbVskFXKh2RabutS5jFs VqAkVp5ZX9wfr7EQwjPuedisP338ht/0ZR/bA4lQ2PNSaNDaF0qo3sWvXhdq xA*AMkF1IlP6h5lOHBdTCyGRU4OdMIxzXu/3j9Nc0XcUTXx*o0p9bfzvkT8j etn8BWHk4dxICr3ECREfw4kEI5BCICXWoSoHfap/XuWPsvBdz7Zo8jP4WG*X xNpz7bN9d4dxqz0GlBc4Re2lxKBQEyDi77li3vifTMb9vsUG82RowdQ6CVgv qGB13iVlV*Zxbd9w9lmMsrAJQO9I90L7Ax7YtA6ORcP/FAX0tjsJP9g4QUIz JEVRcaSD6RTvGt6H9wMa6XjwNv9urs4XXY6At*oSR*yQAG51rBV792bzVZnr YaHftPRDOFSDI1Eq2xyHb5rMQ5Z207XyEkIna8NpYVgG5bDx*Am42L5UEcVZ LTlyjtSPer*7a4v1w*ATl9nawNYgpucUeEZPzgjHmFag34i6xa5CQnyBLBu5 3oLhBYIHzRdZHhuEZ9qa0A7t0u*scj4z6r1Q/NmURTP6NGPrJOyIVPhinZYk 0zVG9fB4lI4ZYEERa35WHGaIwpRn6dsSc7WRma270ZOU24CY*cV4AqEFIDQN mLJvGRuPY*88UvCmYpmK7ByKzZVMVz5uykgQQxjz78vRAwY5duNK88LfC3*r tU2P7X4Mg3gerV83tj1syVVZwzds8hxrK/s9sJvbAC8DJ2NgTI/Oz6*//*YqN4 6vQHMu0QVbQWmx61bTdVZmCccyyDsUD89ZNmcCBK2B9Ca6nwLSzAfLjYI11Y GmXlh5eCipfVuNFiMPK4UR4UB3Kqnmdxjwe6NY5VCJmJaNmrR2hCURQ4SkBO UlJYH74fezSkg516Xd1GN49EPHWSo6m9FSrVYRvT0ZEaM8IfuWc9USbg6kEt Y561Yfv1MucHPldCCi/5h2JOwTF6ks2S8yqLkPcnpEfGTdEou0WOyiw2*H6w onJYb2*UyYcvPW/OsOynFQzI5B9hfOMw/olAgBreSHGdDxZWCsvmAKNyI7ko TAs5H2QbhjcaWsabWGxsgJhioJtq5Eqt0FaT7BJm5PwatXBnydh1MYBiXtqi 8M9GsdclxtOhGQfV*ENHU0W8G7ioBu/V0XkRun0VR9cOWZ9asLQHaLfD/drL ZOIB2IS2Gvm63qSdgKVAmGauGrktVcIeAX89Lq3NmHo2YjmKqMQVfMuGWkbi bEHozBG/9D681P7aAs2b6yyA9WjJxbvDXPLAOFk3jYCcFo/6MPsyieRlePrW aSl7Yr*ct6xZMnX1Ch1E1i88oHWKuKNVMOriAnCKkKHLivqxBihq5r9bruAM M8kiHfI6NLCj/w0tT7Z5w7GLsGMRPh1D7Dzv9F0fB0U0AyPnv45R3lzPbIfa 9ub*hUrPr9Sy/VAiVX5*Ogbgia1wp27h5Jy*H8VGkbSr*79u4826lCDsz5Wq";
        try {
            System.out.println(DesUtils.decryptDES(str));
        } catch (Exception e) {
            System.out.println("error");
        }*/
    }
}