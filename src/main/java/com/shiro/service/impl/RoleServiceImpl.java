package com.shiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shiro.dao.RoleDao;
import com.shiro.model.Role;
import com.shiro.service.ResourceService;
import com.shiro.service.RoleService;
import com.shiro.util.Page;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private ResourceService resourceService;

    public Role createRole(Role role) {
        return roleDao.createRole(role);
    }

    public Role updateRole(Role role) {
        return roleDao.updateRole(role);
    }

    public void deleteRole(Long roleId) {
        roleDao.deleteRole(roleId);
    }

    public Role findOne(Long roleId) {
        return roleDao.findOne(roleId);
    }

    public List<Role> findAll() {
        return roleDao.findAll();
    }

    public Set<String> findRoles(Long... roleIds) {
        Set<String> roles = new HashSet<String>();
        for(Long roleId : roleIds) {
            Role role = findOne(roleId);
            if(role != null) {
                roles.add(role.getRole());
            }
        }
        return roles;
    }

    public Set<String> findPermissions(Long[] roleIds) {
        Set<Long> resourceIds = new HashSet<Long>();
        for(Long roleId : roleIds) {
            Role role = findOne(roleId);
            if(role != null) {
                resourceIds.addAll(role.getResourceIds());
            }
        }
        return resourceService.findPermissions(resourceIds);
    }

	@Override
	public Page<Role> fingPages(int pageNo, int pageSize, Map<String, String> map) {
		return roleDao.fingPages(pageNo, pageSize, map);
	}
}
