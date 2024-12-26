import java.util.*;

class MediaIterator implements Iterator<ImageProxy> {
    private Iterator<ImageProxy> iterator;
    private final List<ImageProxy> mediaList;

    public MediaIterator(List<ImageProxy> mediaList) {
        this.mediaList = mediaList;
        this.iterator = mediaList.iterator();
    }

    public void setFilterKeyword(String keyword) {
        this.iterator = this.mediaList.stream()
                .filter(i -> i.getClass().getSimpleName().toLowerCase().contains(keyword))
                .iterator();
    }

    public void resetIterator() {
        this.iterator = this.mediaList.iterator();
    }

    public boolean hasNext() {
        return iterator.hasNext();
    }

    public ImageProxy next() {
        return iterator.next();
    }
}
