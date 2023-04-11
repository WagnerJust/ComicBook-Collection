//package com.techelevator.dao;
//
//import com.techelevator.model.ComicCollections;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class MemoryCollectionsDao implements ComicCollectionsDao {
//
//    private List<ComicCollections> comicCollectionsList = new ArrayList<>();
//
//    public MemoryCollectionsDao() {
//       if(comicCollectionsList.size() == 0){
//           setCollection();
//       }
//    }
//
//    private void setCollection(){
//        comicCollectionsList.add( new ComicCollections("Avengers", 25,2, true));
//        comicCollectionsList.add(new ComicCollections("Justice League", 45,2, false));
//        comicCollectionsList.add(new ComicCollections("Star Wars", 85,1, true));
//        comicCollectionsList.add(new ComicCollections("Superman", 2,3, true));
//
//
//    }
//
//
//    @Override
//    public List<ComicCollections> listAll(){
//        List<ComicCollections> publicList = new ArrayList<>();
//        for(ComicCollections comicCollection: comicCollectionsList){
//            if(comicCollection.isPublic()){
//                publicList.add(comicCollection);
//            }
//        }
//        return publicList;
//    }
//
//    @Override
//    public List<ComicCollections> collectionsByUserId(int userId) {
//        List<ComicCollections> list = new ArrayList<>();
//        for(ComicCollections collection : comicCollectionsList){
//            if(collection.getUserId() == userId){
//                list.add(collection);
//            }
//        }
//        return list;
//    }
//
//}
