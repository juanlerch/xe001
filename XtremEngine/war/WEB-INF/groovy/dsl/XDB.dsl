xdb    = {xe.server.setXDB(new com.labs.xe.server.xdb.gae.XDBGAE2())}

save   =  {d1->d2 = xe.db.save(d1)
               d2}

delete =  {d1->d2 = xe.db.delete(d1)
               d2}

query  =  {q1->r1 = xe.db.query(d1)
               r1}


             