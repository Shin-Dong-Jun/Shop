package com.example.bravobra.dto;

public class PageHandler {
    private int totalCnt;
    private int pageSize;
    private int naviSize = 10;
    private int totalPage;
    private int page;
    private int beginPage;
    private int endPage;
    private boolean showPrev;
    private boolean showNext;

    public PageHandler(int totalCnt, int page) {
        this(totalCnt, page, 10);
    }

    public PageHandler(int totalCnt, int page, int pageSize) {
        this.totalCnt = totalCnt;
        this.page = page;
        this.pageSize = pageSize;

        totalPage = (int)Math.ceil(totalCnt / (double)pageSize); // 남는 페이지가 있을 수 있기 때문에 올림처리
        beginPage = (page+1) / naviSize * naviSize;
        endPage = Math.min(beginPage + naviSize -1,  totalPage); // endPage가 totalPage보다 작을 수 있기 때문에
        showPrev = beginPage != 0; // 시작 페이지가 0일 때만 안 나오면 된다.
        showNext = endPage != totalPage; // 다음으로 갈게 없으니까 보여주지 않음
    }

    public void print() {
        System.out.println("page = " + page);
        System.out.print(showPrev ? "[PREV] " : "");
        for(int i = beginPage; i<= endPage; i++) {
            System.out.print(i + " ");
        }
        System.out.println(showNext ? " [NEXT]" : "");
    }

    public int getTotalCnt() {
        return totalCnt;
    }

    public void setTotalCnt(int totalCnt) {
        this.totalCnt = totalCnt;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getNaviSize() {
        return naviSize;
    }

    public void setNaviSize(int naviSize) {
        this.naviSize = naviSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getBeginPage() {
        return beginPage;
    }

    public void setBeginPage(int beginPage) {
        this.beginPage = beginPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public boolean isShowPrev() {
        return showPrev;
    }

    public void setShowPrev(boolean showPrev) {
        this.showPrev = showPrev;
    }

    public boolean isShowNext() {
        return showNext;
    }

    public void setShowNext(boolean showNext) {
        this.showNext = showNext;
    }

    @Override
    public String toString() {
        return "PageHandler [totalCnt=" + totalCnt + ", pageSize=" + pageSize + ", naviSize=" + naviSize
                + ", totalPage=" + totalPage + ", page=" + page + ", beginPage=" + beginPage + ", endPage=" + endPage
                + ", showPrev=" + showPrev + ", showNext=" + showNext + "]";
    }


}
//public class PageHandler {
//    private final int totalCnt;
//    private final int pageSize;
//    private final int naviSize = 10;
//    private final int totalPage;
//    private final int page;
//    private final int beginPage;
//    private final int endPage;
//    private final boolean showPrev;
//    private final boolean showNext;
//
//    public PageHandler(int totalCnt, int page, int pageSize) {
//        this.totalCnt = totalCnt;
//        this.page = page;
//        this.pageSize = pageSize;
//
//        this.totalPage = (int) Math.ceil(totalCnt / (double) pageSize);
//        this.beginPage = ((page - 1) / naviSize) * naviSize + 1;
//        this.endPage = Math.min(beginPage + naviSize - 1, totalPage);
//        this.showPrev = beginPage > 1;
//        this.showNext = endPage < totalPage;
//    }
//
//    @Override
//    public String toString() {
//        return String.format(
//                "PageHandler [totalCnt=%d, pageSize=%d, totalPage=%d, page=%d, beginPage=%d, endPage=%d, showPrev=%b, showNext=%b]",
//                totalCnt, pageSize, totalPage, page, beginPage, endPage, showPrev, showNext);
//    }
//
//    // Getter 메서드만 유지
//    public int getTotalCnt() { return totalCnt; }
//    public int getPageSize() { return pageSize; }
//    public int getTotalPage() { return totalPage; }
//    public int getPage() { return page; }
//    public int getBeginPage() { return beginPage; }
//    public int getEndPage() { return endPage; }
//    public boolean isShowPrev() { return showPrev; }
//    public boolean isShowNext() { return showNext; }
//}
