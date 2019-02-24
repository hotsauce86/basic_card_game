import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DeckOfCards {

    List<card> Deck = new List<card>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator<card> iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return null;
        }

        @Override
        public boolean add(card card) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends card> c) {
            return false;
        }

        @Override
        public boolean addAll(int index, Collection<? extends card> c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public card get(int index) {
            return null;
        }

        @Override
        public card set(int index, card element) {
            return null;
        }

        @Override
        public void add(int index, card element) {

        }

        @Override
        public card remove(int index) {
            return null;
        }

        @Override
        public int indexOf(Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(Object o) {
            return 0;
        }

        @Override
        public ListIterator<card> listIterator() {
            return null;
        }

        @Override
        public ListIterator<card> listIterator(int index) {
            return null;
        }

        @Override
        public List<card> subList(int fromIndex, int toIndex) {
            return null;
        }
    };


}
