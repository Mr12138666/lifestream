package com.sunrisejay.lifestream.kv.biz.domain.repository;

import com.sunrisejay.lifestream.kv.biz.domain.dataobject.NoteContentDO;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface NoteContentRepository extends CassandraRepository<NoteContentDO, UUID> {

}
