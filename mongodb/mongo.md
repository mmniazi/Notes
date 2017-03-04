# MongoDb

## DB
- `use DATABASE_NAME`
- `show dbs`
- Db will show when you insert first document

## Drop
`db.dropDatabase()`

## Create Collection
- `db.createCollection(name, options)`
- If you insert a document in collection it is automatically created.
- `show collections`

## Drop Collection
`db.collection.drop()`

## Insert Collection
- `db.COLLECTION_NAME.insert(document)` for inserting
- `db.COLLECTION_NAME.save(document)` with predefined `_id` for updating document

## Find
- `db.COLLECTION_NAME.find()`
- `db.COLLECTION_NAME.find().pretty()`
- `db.COLLECTION_NAME.find({"by":"me"}).pretty()`
- `db.COLLECTION_NAME.find({"likes":{$lt:50}}).pretty()`
- $lt, $gt, $lte, $gte, $ne
- For `AND` condition pass multiple parameters seperated by comma:
`db.mycol.find({key1:value1, key2:value2}).pretty()`
- FOR `OR` condition pass an array of parameters to $or property:
`db.mycol.find({$or:[{"b":"B"},{"a": "A"}]}).pretty()`
- You can use AND and OR together

 ## Update & Save
 - Update update values while save replace document with new one
 - `db.COLLECTION_NAME.update(SELECTIOIN_CRITERIA, UPDATED_DATA)`
 - `db.COLLECTION_NAME.update({'title':'B'},{$set:{'title':'A'}},{multi:true})`
 - `db.COLLECTION_NAME.save({_id:ObjectId(),"title": "A"})`

## Remove
- `db.COLLECTION_NAME.remove(DELLETION_CRITTERIA, JUST_ONE)`
- `db.COLLECTION_NAME.remove({'title':'A'})`
- `db.COLLECTION_NAME.remove()` will remove all collections

## Projection
- To get only selected fields set keys values to 1 and 0
- `db.COLLECTION_NAME.find(SELECTIOIN_CRITERIA,KEYS)`
- `db.mycol.find({},{"title":1,_id:0})`

## Limit records
- `db.mycol.find({},{"title":1,_id:0}).limit(2)`
- `db.COLLECTION_NAME.find().limit(NUMBER).skip(NUMBER)` for skipping initial results

## Sorting
- `db.COLLECTION_NAME.find().sort({KEY:1/-1})`
- `db.COLLECTION_NAME.find({},{"title":1,_id:0}).sort({"title":-1})`

## Indexing
- `db.COLLECTION_NAME.ensureIndex({KEY:1/-1})` will index the field in ascending or descending order
- `db.COLLECTION_NAME.ensureIndex({"title":1,"description":-1})`

## Aggregation
- Aggregations operations process data records and return computed results
```
db.COLLECTION_NAME.aggregate([  
   {  
      $group:{  
         _id:"$by_user",
         num_tutorial:{  
            $sum:1
         }
      }
   }
])
``` 