import java.util.Iterator;
import java.util.List;


class MediaIterator implements Iterator<ImageProxy> {
    private Iterator<ImageProxy> imageIterator;
    private final List<ImageProxy> imageList;

    public MediaIterator(List<ImageProxy> mediaList) {
        this.imageList = mediaList;
        this.imageIterator = mediaList.iterator();
    }

    public void setFilterKeyword(String keyword) {
        this.imageIterator = this.imageList.stream().filter(i-> i.name.toLowerCase().contains(keyword)).iterator();
    }

    public void resetIterator() {
        this.imageIterator = this.imageList.iterator();
    }

    @Override
    public boolean hasNext() {
        return this.imageIterator.hasNext();
    }

    @Override
    public ImageProxy next() {
            return imageIterator.next();
    }
}