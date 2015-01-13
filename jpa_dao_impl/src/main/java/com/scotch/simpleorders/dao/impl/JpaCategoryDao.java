package com.scotch.simpleorders.dao.impl;

import com.scotch.simpleorders.dao.CategoryDao;
import com.scotch.simpleorders.entity.Category;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by sutupin on 30.12.2014.
 */

public class JpaCategoryDao extends AbstractJpaDao<Category, Integer> implements CategoryDao {
}
